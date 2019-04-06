package com.example.snippet.app;

//CSV to Java POJO

import com.example.snippet.domain.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@SpringBootApplication
public class SnippetApplication {

  List<Person> personList;

  public static void main(String[] args) {
    ApplicationContext context = SpringApplication.run(SnippetApplication.class, args);
    AppConfig config = context.getBean(AppConfig.class);

    SnippetApplication app = new SnippetApplication();
    app.inputToList(config);

  }

  private void inputToList(AppConfig config) {

    List<Person> persons = new ArrayList<>();

    try {
      InputStream resource = new ClassPathResource(config.getInput()).getInputStream();
      BufferedReader reader = new BufferedReader(new InputStreamReader(resource));
      persons = reader.lines()
          .skip(1)
          .map(mapToItem)
          .filter(p -> p.getAge() < 50)
          .filter(p -> p.getGender().equalsIgnoreCase("Male"))
          .collect(Collectors.toList());

    } catch (IOException e) {
      e.printStackTrace();
    }

    for (Person p : persons) {
      System.out.println(p);
    }
  }

  private Function<String, Person> mapToItem = (line) -> {
    String[] p = line.split(",");
    Person person = new Person();

    person.setFirstName(p[0]);
    person.setLastName(p[1]);
    person.setGender(p[2]);
    person.setAge(Integer.parseInt(p[3]));
    return person;
  };


}
