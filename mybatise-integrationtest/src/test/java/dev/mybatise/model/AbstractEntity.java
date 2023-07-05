package dev.mybatise.model;

import dev.mybatise.typehander.LocalDateTimeTypeHandler;
import io.github.mybatise.annotation.Column;
import org.apache.ibatis.type.JdbcType;

import java.time.LocalDateTime;

/**
 * @author Wes Lin
 */
public abstract class AbstractEntity {

    @Column(name = "created_when", typeHandler = LocalDateTimeTypeHandler.class, jdbcType = JdbcType.VARCHAR)
    private LocalDateTime createdWhen;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "updated_when",typeHandler = LocalDateTimeTypeHandler.class)
    private LocalDateTime updatedWhen;
    @Column(name = "updated_by")
    private String updatedBy;

    public LocalDateTime getCreatedWhen() {
        return createdWhen;
    }

    public void setCreatedWhen(LocalDateTime createdWhen) {
        this.createdWhen = createdWhen;
    }

    public LocalDateTime getUpdatedWhen() {
        return updatedWhen;
    }

    public void setUpdatedWhen(LocalDateTime updatedWhen) {
        this.updatedWhen = updatedWhen;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
