package com.fabio.parkingapi;

import com.fabio.parkingapi.entities.Parking;
import com.fabio.parkingapi.entities.PriceTable;
import com.fabio.parkingapi.entities.enums.PeriodType;
import com.fabio.parkingapi.repositories.ParkingRepository;
import com.fabio.parkingapi.repositories.PriceTableRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ParkingapiApplication implements CommandLineRunner {


	private final ParkingRepository parkingRepository;
	private final PriceTableRepository priceTableRepository;


	public ParkingapiApplication(ParkingRepository parkingRepository, PriceTableRepository priceTableRepository) {
		this.parkingRepository = parkingRepository;
		this.priceTableRepository = priceTableRepository;
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

		PriceTable p1 = new PriceTable(1L,"Até 15 minutos",5.00);
		PriceTable p2 = new PriceTable(2L,"Entre 15 e 30 minutos",7.00);
		PriceTable p3 = new PriceTable(3L,"Entre 31 e 45 minutos",9.00);
		PriceTable p4 = new PriceTable(4L,"Entre 46 e 60 minutos",12.00);
		PriceTable p5 = new PriceTable(5L,"Adicional Hora",5.00);
		PriceTable p6 = new PriceTable(6L,"Diaria",30.00);
		PriceTable p7 = new PriceTable(7L,"Mensal",200.00);

		priceTableRepository.saveAll(List.of(p1,p2,p3,p4,p5,p6,p7));

	}
}
