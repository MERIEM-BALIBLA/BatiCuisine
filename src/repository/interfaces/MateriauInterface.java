package repository.interfaces;

import entity.Materiau;

import java.util.List;

public interface MateriauInterface {
    List<Materiau> selectMateriaux(int projet_id);
}
