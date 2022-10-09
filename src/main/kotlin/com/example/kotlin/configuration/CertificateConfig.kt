package com.example.kotlin.configuration

import com.example.kotlin.dal.dto.CreditInfoDto
import com.example.kotlin.dal.dto.RequestDto
import com.example.kotlin.dal.dto.SoapRequestDto
import lombok.RequiredArgsConstructor
import org.apache.commons.lang3.CharSequenceUtils.toCharArray
import org.apache.http.client.methods.HttpPost
import org.apache.http.conn.ssl.NoopHostnameVerifier
import org.apache.http.conn.ssl.SSLConnectionSocketFactory
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.HttpClients
import org.apache.http.message.BasicHeader
import org.apache.http.ssl.SSLContexts
import org.apache.http.ssl.TrustStrategy
import org.apache.http.util.EntityUtils
import org.json.JSONObject
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import java.io.FileInputStream
import java.net.URI
import java.security.KeyStore
import java.security.cert.X509Certificate


@Configuration
@RequiredArgsConstructor
class CertificateConfig {

    @Value("\${login}")
    private lateinit var username: String
    @Value("\${password}")
    private lateinit var password: String
    @Value("\${basic-auth}")
    private lateinit var basicAuth: String
    @Value("\${cert-path}")
    private lateinit var certPath: String
    @Value("\${cert-password}")
    private lateinit var certPassword: String
    @Value("\${soap-action-uri}")
    private lateinit var soapActionUri: String
    @Value("\${credit-history-uri}")
    private lateinit var creditHistoryUri: String
    @Value("\${credit-info-uri}")
    private lateinit var creditInfiUri: String
    @Value("\${token-uri}")
    private lateinit var tokenUri: String

    private val webclient: WebClient = WebClient.create()

    fun getToken(): String{

        val requestDto = RequestDto(
            username = username,
            password = password
        )

        val result = webclient.post()
            .uri(tokenUri)
            .bodyValue(requestDto)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono<String>()
            .block()

        return JSONObject(result).getString("access_token")
    }

    fun getCreditInfoId(inn: String): CreditInfoDto? =
         webclient.post()
            .uri(creditInfiUri)
            .accept(MediaType.APPLICATION_JSON)
            .headers { httpHeaders -> httpHeaders.setBearerAuth(getToken()) }
            .retrieve()
            .bodyToMono<CreditInfoDto>()
            .block()


    fun getCreditInfoReport(creditInfoId: Int): String{

        val trustStrategy =
            TrustStrategy { chain: Array<X509Certificate?>?, authType: String? -> true }

        val clientStore = KeyStore.getInstance("PKS12")
        val inputStream = FileInputStream(certPath)

        try {
            clientStore.load(inputStream, toCharArray(certPassword))
        }finally {
            inputStream.close()
        }

        val sslContext = SSLContexts.custom()
            .loadKeyMaterial(clientStore, toCharArray(certPassword))
            .loadTrustMaterial(trustStrategy)
            .build()

        val sslConnectionFactory = SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE)

        val httpClient = HttpClients.custom()
            .setSSLSocketFactory(sslConnectionFactory)
            .build()

        val uri = URI(creditHistoryUri)
        val request = HttpPost(uri)
        val header = BasicHeader(HttpHeaders.AUTHORIZATION, basicAuth)

        request.setHeader(header)
        request.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_XML_VALUE)
        request.setHeader(HttpHeaders.ACCEPT, MediaType.TEXT_XML_VALUE)
        request.setHeader("SOAPAction", soapActionUri)

        val bodyEntity = StringEntity(SoapRequestDto.getRequest(creditInfoId))
        val contentHeader = BasicHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_XML_VALUE)
        bodyEntity.contentType = contentHeader
        request.entity = bodyEntity

        val response = httpClient.execute(request)
        val entity = response.getEntity()

        return EntityUtils.toString(entity)
    }
}
