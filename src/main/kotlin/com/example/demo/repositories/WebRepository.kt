package com.example.demo.repositories

import com.example.demo.models.what.ProductData
import org.jsoup.Jsoup
import org.springframework.stereotype.Repository
import java.net.CookieManager
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse


@Repository
class WebRepository {

    fun request(input1: String, input2: String, input3: String): String? {
        val requestBody =
            "tipo_buscador=principal&pagina=1&razon_social=&numero1=$input1&numero2=$input2&numero3=$input3&clave=&categoria=&actividad=&comunidad=&provincia=&localidad=&Buscar=Buscar%2FSearch"

        val cookieManager = CookieManager()

        val client = HttpClient.newBuilder()
            .followRedirects(HttpClient.Redirect.NORMAL)
            .cookieHandler(cookieManager)
            .build();
        val request = HttpRequest.newBuilder()
            .uri(URI.create("https://rgsa-web-aesan.mscbs.es/rgsa/procesaBusquedaServlet?$requestBody"))
            .POST(HttpRequest.BodyPublishers.noBody())
            .header("Content-Type", "application/x-www-form-urlencoded")
            .header(
                "User-Agent",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36)"
            )
            .build();

        //first request
        client.send(request, HttpResponse.BodyHandlers.ofString());

        val request2 = HttpRequest.newBuilder()
            .uri(URI.create("https://rgsa-web-aesan.mscbs.es/rgsa/procesaBusquedaServlet?tipo_buscador=principal&0.x=16&0.y=5"))
            .POST(HttpRequest.BodyPublishers.noBody())
            .header("Content-Type", "application/x-www-form-urlencoded")
            .header(
                "User-Agent",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36)"
            )
            .build();

        //second request with final response
        val response2 = client.send(request2, HttpResponse.BodyHandlers.ofString());

        return response2.body();
    }

    fun parse(html: String): Pair<String, ProductData> {
        // val headers = Jsoup.parse(html).select("table").get(0).select("th").map { a -> a.html() }

        val details = Jsoup.parse(html).select("table")
            .get(0)
            .select("tbody")
            .get(0)
            .select("td")
            .map { a -> a.html() }
            .toList()

        return Pair(
            details.get(0), ProductData(
                enterprise_name = details.get(1),
                date_registering = details.get(2),
                address = details.get(3),
                city = details.get(4),
                province = details.get(5),
                activities = emptyList(),
                social_address = ""
            )
        )


    }


}