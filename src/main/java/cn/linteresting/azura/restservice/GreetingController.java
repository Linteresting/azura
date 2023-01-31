package cn.linteresting.azura.restservice;

import cn.hutool.core.util.StrUtil;
import cn.linteresting.azura.dto.InitSetting;
import cn.linteresting.azura.repository.InitSettingRepo;
import cn.linteresting.azura.vo.Greeting;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class GreetingController implements ApplicationContextAware {

    private final AtomicInteger counter = new AtomicInteger();
    private static final String template = "hello! %s";
    private ApplicationContext context;
    @Autowired
    InitSettingRepo initSettingRepo;

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "default user") String name) {
        int count = counter.incrementAndGet();
        initSettingRepo.updateConfigValueByConfigKey(String.valueOf(count), "count");
        return new Greeting(count, String.format(template, name));
    }

    @GetMapping("/getProperty")
    public String getProperty(@RequestParam(value = "name", defaultValue = "") String propertyName) {
        Environment environment = context.getEnvironment();
        return environment.getProperty(propertyName);
    }

    @GetMapping("/getCount")
    public int getCount() {
        int count = 0;
        for (InitSetting next : initSettingRepo.findAll()) {
            if (StrUtil.equals(next.getConfigKey(), "count")) {
                count = Integer.parseInt(next.getConfigValue());
            }
        }
        return count;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

}
