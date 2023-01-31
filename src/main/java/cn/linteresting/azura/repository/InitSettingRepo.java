package cn.linteresting.azura.repository;

import cn.linteresting.azura.dto.InitSetting;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

public interface InitSettingRepo extends CrudRepository<InitSetting, Integer> {
    @Transactional
    @Modifying
    @Query("update InitSetting i set i.configValue = :configValue where i.configKey = :configKey")
    void updateConfigValueByConfigKey(@NonNull @Param("configValue") String configValue, @NonNull @Param("configKey") String configKey);

}
