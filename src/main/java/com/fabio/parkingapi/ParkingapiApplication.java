package com.fabio.parkingapi;

import com.fabio.parkingapi.entities.Parking;
import com.fabio.parkingapi.entities.enums.PeriodType;
import com.fabio.parkingapi.repositories.ParkingRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ParkingapiApplication implements CommandLineRunner {


	private final ParkingRepository parkingRepository;

	public ParkingapiApplication(ParkingRepository parkingRepository) {
		this.parkingRepository = parkingRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(ParkingapiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Parking c1 = new Parking(1L,"WWW-9090","Ford Ka","Branco", PeriodType.FRACIONADO);
		Parking c2 = new Parking(2L,"QQQ-8888","Fiat Uno","Cinza",PeriodType.DIARIA);
		Parking c3 = new Parking(3L,"YYY-5544","Renault Longa","Prata",PeriodType.MENSAL);

		parkingRepository.saveAll(List.of(c1,c2,c3));

	}
}
