package com.spring.retry.infrastructure.rest.in;

import com.spring.retry.application.port.out.KamikazechaserPortOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class SpringRetryRestController {
	@Autowired
	KamikazechaserPortOut kamikazechaserPortOut;

	@GetMapping(path = "/getData")
	public Object getData(@RequestParam(name = "param") String param) {
		Object response = kamikazechaserPortOut.getData(param);
		return response;
		
	}
}