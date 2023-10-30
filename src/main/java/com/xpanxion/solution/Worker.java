package com.xpanxion.solution;

import java.util.*;
import java.util.stream.Collectors;

import com.xpanxion.java.assignments.DataAccess;
import com.xpanxion.java.assignments.model.*;

import static com.xpanxion.java.assignments.DataAccess.*;

public class Worker {
    public void ex1 () {

        /*System.out.println(getProducts().stream()
                .map(f -> new Product(f.getId(), f.getDepartmentId(), getDepartments().get(f.getDepartmentId()-1).getName(), f.getName(), f.getPrice(), f.getSku()))
                .collect(Collectors.toList()));*/

        HashMap<Integer, String> hash = new HashMap<>();

        List<Product> list1 = getProducts();

        List<Department> list2 = getDepartments();


        for(int i = 0; i<list2.size();i++){
            hash.put(list2.get(i).getId(),list2.get(i).getName());
        }

        System.out.println(getProducts().stream()
                .map(f -> new Product(f.getId(), f.getDepartmentId(), hash.get(f.getDepartmentId()), f.getName(), f.getPrice(), f.getSku()))
                .collect(Collectors.toList()));


        //var newList = oldList.getProducts().stream().map()
        //System.out.println(list2);
    }

    public void ex2 () {

        System.out.println(getProducts().stream()
                .map(f -> new Product(f.getId(), f.getDepartmentId(), "N/A", f.getName(), f.getPrice(), f.getSku()))
                .collect(Collectors.toList()));


        //var newList = oldList.getProducts().stream().map()
        //System.out.println(list);
    }

    public void ex3 () {

        System.out.println(getProducts().stream()
                .filter(p->p.getDepartmentId()==1 && p.getPrice()>=10).collect(Collectors.toList()));


        //var newList = oldList.getProducts().stream().map()
        //System.out.println(list);
    }

    public void ex4 () {

        /*System.out.println(getProducts().stream()
                .map(f -> new Product(f.getId(), f.getDepartmentId(), getDepartments().get(f.getDepartmentId()-1).getName(), f.getName(), f.getPrice(), f.getSku()))
                .collect(Collectors.toList()));

        System.out.println(getProducts().stream().filter(p->p.getDepartmentName().equals("Food")).mapToDouble(Product::getPrice).sum());*/

        List<Product>prodList = DataAccess.getProducts().stream()
                .map(f -> new Product(f.getId(), f.getDepartmentId(), DataAccess.getDepartments().get(f.getDepartmentId()-1).getName(), f.getName(), f.getPrice(), f.getSku()))
                .collect(Collectors.toList());
        Double sum = prodList.stream().filter(Product->Product.getDepartmentName().equals("Food")).mapToDouble(Product::getPrice).sum();
        System.out.println(sum);
        //System.out.println(list);
    }

    public void ex5 () {
        List<Person>persList = DataAccess.getPeople().stream()
                .map(p -> new Person(p.getId(), p.getFirstName(), p.getLastName(), p.getAge(), p.getSsn().substring(7)))
                .collect(Collectors.toList());
        System.out.println(persList.stream()
                .filter(p->p.getId()<=3).collect(Collectors.toList()));

    }

    public void ex6 () {

        List<Cat> catList = DataAccess.getCats();
        Collections.sort(catList);
        catList.stream().forEach(System.out::print);

    }

    public void ex7 () {

        var words = getWords().split(" ");

        HashMap<String, Integer> hash = new HashMap<>();

        for(String w: words){

            hash.put(w,hash.getOrDefault(w,0)+1);

        }

        TreeMap<String, Integer> sorted = new TreeMap<>();

        sorted.putAll(hash);

        for (Map.Entry<String, Integer> entry : sorted.entrySet())
            System.out.println(entry.getKey() +
                    " = " + entry.getValue());

    }

    public void ex8 (){

        List<Person>persList = DataAccess.getPeople().stream()
                .map(p -> new Person(p.getId(), p.getFirstName(), "null", 0, "null"))
                .collect(Collectors.toList());

    }

    public void ex9 (){

        List<Product>prodList = DataAccess.getProducts().stream()
                .map(f -> new Product(f.getId(), f.getDepartmentId(), DataAccess.getDepartments().get(f.getDepartmentId()-1).getName(), f.getName(), f.getPrice(), f.getSku()))
                .collect(Collectors.toList());

        List<Product> filtered = prodList.stream()
                .filter(p->p.getDepartmentName().equals("Electronics")).map(f -> new Product(f.getId(), f.getDepartmentId(), DataAccess.getDepartments().get(f.getDepartmentId()-1).getName(), f.getName(), f.getPrice()+2, f.getSku()))
                .collect(Collectors.toList());;

        Double sum = filtered.stream().mapToDouble(Product::getPrice).sum();

        System.out.println("$" + sum);
    }

    public void ex10 (){

        List<Cat> cats = DataAccess.getCats();
        DataAccess.getPeople()
                .stream()
                .map(p -> new PersonCat(p.getId(), p.getFirstName(),
                        cats.stream()
                                .filter(c -> p.getId() == c.getId())
                                .collect(Collectors.toList())))
                .forEach(System.out::print);
    }
}
