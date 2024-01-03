package md.sotea.journal_as_a_table.service;

import java.util.List;

import md.sotea.journal_as_a_table.entities.Activity;

public interface ActivityService {

    List<Activity> getAllBySearch(String q);

    List<Activity> getAll();

    Activity getOne(Long id);

    Activity saveNew(Activity activity);

    Activity update(Activity activity, Long id);

    void delete(Long id);

}
