package pro.sky.telegrambot.configuration.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pro.sky.telegrambot.configuration.model.NotificationTask;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationTaskRepository extends JpaRepository<NotificationTask, Long> {

    @Query("SELECT nt FROM NotificationTask nt WHERE nt.dateTime = :dateTime")
    List<NotificationTask> findByDateTime(@Param("dateTime") LocalDateTime dateTime);
}