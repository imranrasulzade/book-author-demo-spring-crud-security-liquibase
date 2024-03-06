package com.matrix.springpracticeapp.mapper.exampleOfModelMapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class ExampleModelMapper {

    public static void simpleModelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        SourceCl sourceCl = new SourceCl(25, "NAME", "994");
        TargetCl targetCl = new TargetCl();

        modelMapper.map(sourceCl, targetCl);

        System.out.println("Source value id: " + targetCl.getId());
        System.out.println("Source value name: " + targetCl.getName());
    }

    public static void propertyModelMapper(){
        ModelMapper modelMapper = new ModelMapper();

        PropertyMap<SourceCl, TargetCl> propertyMap = new PropertyMap<>() {
            protected void configure() {
                map().setMobile(source.getPhone());
            }
        };

        modelMapper.addMappings(propertyMap);

        SourceCl sourceCl = new SourceCl(25, "NAME", "994");
        TargetCl targetCl = new TargetCl();
        modelMapper.map(sourceCl, targetCl);
        System.out.println("Target mobile: " + targetCl.getMobile());
    }
}
