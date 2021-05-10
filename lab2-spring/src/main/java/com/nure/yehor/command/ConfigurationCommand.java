package com.nure.yehor.command;

import com.nure.yehor.entity.Configuration;
import com.nure.yehor.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
@ShellCommandGroup("Configuration commands")
public class ConfigurationCommand {
    private final ConfigurationService configurationService;
    @Value("${limit}")
    private int limit;

    @Autowired
    public ConfigurationCommand(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    @ShellMethod("Create new configuration")
    public String configuration(@ShellOption String name,
                                @ShellOption int c1,
                                @ShellOption int c2,
                                @ShellOption int c3,
                                @ShellOption int c4,
                                @ShellOption int c5) {

        Configuration configuration = Configuration.builder()
                .name(name)
                .c1(c1)
                .c2(c2)
                .c3(c3)
                .c4(c4)
                .c5(c5)
                .build();
        return configurationService.save(configuration).toString();
    }

    @ShellMethod("Read configuration by id")
    public String cfgread(@ShellOption int id) {
        return configurationService.read(id).toString();
    }

    @ShellMethod("Read all configurations")
    public String cfgreadAll() {
        return configurationService.readAll().toString();
    }

    @ShellMethod("Read configurations by page")
    public String cfgreadPage(@ShellOption(defaultValue = "0") int page) {
        return configurationService.readPage(limit, page).toString();
    }

    @ShellMethod("Update configuration")
    public String cfgupdate(@ShellOption int id,
                            @ShellOption String name,
                            @ShellOption int c1,
                            @ShellOption int c2,
                            @ShellOption int c3,
                            @ShellOption int c4,
                            @ShellOption int c5) {

        Configuration configuration = Configuration.builder()
                .id(id)
                .name(name)
                .c1(c1)
                .c2(c2)
                .c3(c3)
                .c4(c4)
                .c5(c5)
                .build();
        configurationService.update(configuration);
        return "Success";
    }

    @ShellMethod("Delete configuration")
    public String cfgdelete(@ShellOption int id) {
        configurationService.delete(Configuration.builder().id(id).build());
        return "Success";
    }

    @ShellMethod("Change configuration limit by page")
    public String cfglimit(@ShellOption int limit) {
        this.limit = limit;
        return "Success";
    }

}
