package org.sid;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.sid.DAO.PatientRepository;
import org.sid.ENTITIES.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@SpringBootApplication
public class TpMvcApplication implements CommandLineRunner{
	@Autowired
private PatientRepository patientRepository=null;
	public static void main(String[] args) {
		SpringApplication.run(TpMvcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		DateFormat df=new SimpleDateFormat("dd/MM/yyyy");
	/*	patientRepository.save(new Patient(null,"marwa",new Date(),false));
		patientRepository.save(new Patient(null,"houda",new Date(),true));
		patientRepository.save(new Patient(null,"zineb",new Date(),false));
		patientRepository.save(new Patient(null,"amine",new Date(),true));
		patientRepository.save(new Patient(null,"mohammed",df.parse("02/09/1998"),false));
		patientRepository.save(new Patient(null,"soufiane",df.parse("02/09/1998"),true));*/
		Page<Patient>patients=patientRepository.findByNameContains("m", PageRequest.of(0, 2));
	   System.out.println(patients.getSize());
	   System.out.println(patients.getTotalElements());	
		System.out.println(patients.getTotalPages());
		patientRepository.findAll().forEach(p->{
	    	System.out.println(p.toString());
	    });
		System.out.println("-----------------------------");
		Page<Patient>patients2=patientRepository.chercher("%m%", false, PageRequest.of(0, 2));
		   System.out.println(patients2.getSize());
		   System.out.println(patients2.getTotalElements());	
			System.out.println(patients2.getTotalPages());
			patients2.getContent().forEach(p->{
		    	System.out.println(p.toString());
		    });
	}

}
