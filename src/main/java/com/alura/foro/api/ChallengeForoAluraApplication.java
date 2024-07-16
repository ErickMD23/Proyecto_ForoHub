package com.alura.foro.api;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class ChallengeForoAluraApplication {

	public static void main(MysqlxDatatypes.Scalar.String[] args) {
		SpringApplication.run(ChallengeForoAluraApplication.class, Arrays.toString(args));
	}

}
