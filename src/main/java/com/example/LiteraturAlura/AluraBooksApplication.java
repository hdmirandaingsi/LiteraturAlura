package com.example.LiteraturAlura;

import com.example.LiteraturAlura.principal.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AluraBooksApplication implements CommandLineRunner {

    @Autowired
    private Principal principal;

    public static void main(String[] args) {
        SpringApplication.run(AluraBooksApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        principal.muestraElMenu();
    }
}