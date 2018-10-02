package co.com.bancolombia.getmovements.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.squareup.okhttp.Response;

import co.com.bancolombia.getmovements.models.DataResponse;
import co.com.bancolombia.getmovements.models.HeaderResponse;
import co.com.bancolombia.getmovements.models.RequestService;
import co.com.bancolombia.getmovements.models.ResponseService;
import co.com.bancolombia.getmovements.restservices.RestClient;

@Component
public class GetMovementsService {
	@Autowired
	private RestClient restclient;

	public ResponseService getMovements(RequestService dataRequest) {
		try {
			dataRequest.getData();
			Response response = restclient.financialData(dataRequest.getData().get(0).getHeader().getToken());
			return validateRestResponse(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private boolean validateDeclaration(JsonObject financialData) {
		return (Integer.parseInt(financialData.get("revenue_Real").getAsString().replaceAll("\\.", "")) > 50000
				|| Integer.parseInt(financialData.get("assets_total").getAsString().replaceAll("\\.", "")) > 80000
				|| Integer.parseInt(financialData.get("liabilities_total").getAsString().replaceAll("\\.", "")) > 90000)
						? true
						: false;
	}

	private ResponseService putResponse(boolean declaration, int code) {
		DataResponse dataResponse = new DataResponse();
		dataResponse.setDeclaration(declaration);
		HeaderResponse header = new HeaderResponse();
		header.setStatus(code);
		dataResponse.setHeader(header);
		ResponseService responseService = new ResponseService();
		ArrayList<DataResponse> list = new ArrayList<>();
		list.add(dataResponse);
		responseService.setData(list);
		return responseService;

	}

	private ResponseService validateRestResponse(Response response) {
		return (response.code() == 200) ? converResponsoToJsonObject(response) : putResponse(false, response.code());
	}

	private ResponseService converResponsoToJsonObject(Response response) {
		try {
			Gson g = new Gson();
			JsonObject p = g.fromJson(response.body().string(), JsonObject.class);
			JsonArray data = (JsonArray) p.get("data");
			JsonObject dataObject = data.get(0).getAsJsonObject();
			return putResponse(validateDeclaration(dataObject.get("financialData").getAsJsonObject()), response.code());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
