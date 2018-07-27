package com.klm;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@RestController
public class ChickenFeedController {


	private static final String baseurl="http://localhost:8082/eggs_collect";
	private static final String baseurl2="http://localhost:8080/chickens_feed";


	@PostMapping(path="/collect_after_feed", produces="application/json")

	public @ResponseBody KLMResult CollectAfterFeed ()
	{
		KLMResult  klm = new KLMResult();
		//.................
		Client cl= Client.create();
		WebResource wr = cl.resource(baseurl);
		ClientResponse cr = wr.accept("application/json").post(ClientResponse.class);
		int feedCode =cr.getClientResponseStatus().getStatusCode();
		Client cl2= Client.create();
		WebResource wr2= cl2.resource(baseurl2);
		ClientResponse cr2 = wr2.accept("application/json").post(ClientResponse.class);

		int collectCode = cr2.getClientResponseStatus().getStatusCode();

		if((feedCode == 200)&&(collectCode == 200))
		{
			klm = new KLMResult();
			klm.setCollect("success");
			klm.setFeed("success");
		}
		if((feedCode == 200)&&(collectCode == 202))
		{
			klm = new KLMResult();
			klm.setCollect("success");
			klm.setFeed("failed");
		}
		if((feedCode == 202)&&(collectCode == 200))
		{
			klm = new KLMResult();
			klm.setCollect("failed");
			klm.setFeed("success");
		}
		if((feedCode == 400)&&(collectCode == 400))
		{
			klm = new KLMResult();
			klm.setCollect("failed");
			klm.setFeed("failed");
		}

		return klm;


	}
}
