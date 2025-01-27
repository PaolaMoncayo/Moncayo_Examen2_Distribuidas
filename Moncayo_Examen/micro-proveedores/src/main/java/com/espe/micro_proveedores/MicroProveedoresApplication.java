package com.espe.micro_proveedores;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MicroProveedoresApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroProveedoresApplication.class, args);
	}

}
