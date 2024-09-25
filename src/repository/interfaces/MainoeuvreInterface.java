package repository.interfaces;

import entity.MainOeuvre;

import java.util.List;

public interface MainoeuvreInterface {
    List<MainOeuvre> selectMainOeuvre(int projet_id);
}
