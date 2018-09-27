package co.com.bancolombia.getmovements.connection;

import org.springframework.stereotype.Component;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;
@Component
public class Connection {

	private static final String PASSWORD="Hamagalujan@123456789";
	private static final String USERNAME="hamaga";
	
	public CloudantClient cloudantConnection() {
		try {
			return ClientBuilder.account(USERNAME).username(USERNAME)
					.password(PASSWORD).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
