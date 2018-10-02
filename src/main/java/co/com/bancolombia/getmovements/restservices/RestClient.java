package co.com.bancolombia.getmovements.restservices;

import org.springframework.stereotype.Component;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

@Component
public class RestClient {

	final static String URL = "https://sbapi.bancolombia.com/v1/sales-services/customer-management/customer-precedents/customers/financial-data";
	final static String AUTHORIZATION = "authorization";
	final static String ACCEPT = "accept";
	final static String APPLICATION_JSON = "application/vnd.bancolombia.v1+json";

	public Response financialData(String token) {
		try {
			OkHttpClient client = new OkHttpClient();
			Request request = new Request.Builder().url(URL).get().addHeader(AUTHORIZATION, "Bearer " + token)
					.addHeader(ACCEPT, APPLICATION_JSON).build();
			return client.newCall(request).execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
