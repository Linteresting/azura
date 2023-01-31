package cn.linteresting.azura.dto;


import javax.persistence.*;

@Entity
@Table(name = "t_init_setting")
public class InitSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    public InitSetting() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "config_key")
    private String configKey;

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    @Column(name = "config_value")
    private String configValue;

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public InitSetting(String configKey, String configValue) {
        this.configKey = configKey;
        this.configValue = configValue;
    }
}
