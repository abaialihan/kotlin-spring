package com.example.kotlin.dal.dto

class SoapRequestDto{
    companion object {
        fun getRequest(idNumber: Int): String {
            return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:cb5=\"http://creditinfo.com/CB5\" xmlns:cus=\"http://creditinfo.com/CB5/v5.59/CustomReport\">\n" +
                    "   <soapenv:Header/>\n" +
                    "   <soapenv:Body>\n" +
                    "      <cb5:GetPdfReport>\n" +
                    "         <cb5:parameters>\n" +
                    "            <cus:Consent>true</cus:Consent>\n" +
                    "            <cus:IDNumber>" + idNumber + "</cus:IDNumber>\n" +
                    "            <cus:IDNumberType>CreditinfoId</cus:IDNumberType>\n" +
                    "            <cus:InquiryReason>AnotherReason</cus:InquiryReason>\n" +
                    "            <cus:InquiryReasonText>TextAnother</cus:InquiryReasonText>\n" +
                    "            <cus:LanguageCode>ru-RU</cus:LanguageCode>\n" +
                    "            <cus:ReportName>CreditinfoReportPlus</cus:ReportName>\n" +
                    "            <cus:SubjectType>Company</cus:SubjectType>\n" +
                    "         </cb5:parameters>\n" +
                    "      </cb5:GetPdfReport>\n" +
                    "   </soapenv:Body>\n" +
                    "</soapenv:Envelope>"
        }
    }
}
