package com.java.business.schedule.mapper;

import com.java.business.schedule.entity.ScheduledTask;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface ScheduledTaskMapper extends Mapper<ScheduledTask> {
}
