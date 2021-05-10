package com.nure.yehor.command;

import com.nure.yehor.entity.Component;
import com.nure.yehor.entity.Type;
import com.nure.yehor.service.ComponentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ShellComponent
@ShellCommandGroup("Components commands")
public class ComponentsCommand {
    private final ComponentsService componentsService;
    @Value("${limit}")
    private int limit;


    @Autowired
    public ComponentsCommand(ComponentsService componentsService) {
        this.componentsService = componentsService;
    }

    @ShellMethod("Create new component")
    public String component(@ShellOption String type, @ShellOption int price, @ShellOption String name) {
        List<String> types = Arrays.stream(Type.values()).map(Enum::name).collect(Collectors.toList());
        Type t = types.contains(type) ? Type.valueOf(type) : Type.NONE;
        Component component = Component.builder()
                .type(t)
                .price(price)
                .name(name)
                .build();
        return componentsService.save(component).toString();
    }

    @ShellMethod("Read component by id")
    public String cmpread(@ShellOption int id) {
        return componentsService.read(id).toString();
    }

    @ShellMethod("Read all components")
    public String cmpreadAll() {
        return componentsService.readAll().toString();
    }

    @ShellMethod("Read page")
    public String cmpreadPage(@ShellOption(defaultValue = "0") int page) {
        return componentsService.readPage(limit, page).toString();
    }

    @ShellMethod("Update component")
    public String cmpupdate(@ShellOption int id, @ShellOption String type, @ShellOption int price, @ShellOption String name) {
        List<String> types = Arrays.stream(Type.values()).map(Enum::name).collect(Collectors.toList());
        Type t = types.contains(type) ? Type.valueOf(type) : Type.NONE;
        Component component = Component.builder()
                .id(id)
                .type(t)
                .price(price)
                .name(name)
                .build();
        componentsService.update(component);
        return "Success";
    }

    @ShellMethod("Delete component")
    public String cmpdelete(@ShellOption int id) {
        componentsService.delete(Component.builder().id(id).build());
        return "Success";
    }

    @ShellMethod("Change component limit by page")
    public String cmplimit(@ShellOption int limit) {
        this.limit = limit;
        return "Success";
    }

}
