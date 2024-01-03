package md.sotea.journal_as_a_table.service.Implementation;

import org.springframework.stereotype.Service;

import md.sotea.journal_as_a_table.entities.Activity;
import md.sotea.journal_as_a_table.exceptions.ResourceNotFoundException;
import md.sotea.journal_as_a_table.repositories.ActivityRepository;
import md.sotea.journal_as_a_table.service.ActivityService;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    ActivityRepository activityRepository;

    public ActivityServiceImpl(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public List<Activity> getAllBySearch(String q) {
        return activityRepository.getAllBySearch(q);
    }

    @Override
    public List<Activity> getAll() {
        return activityRepository.findAll();
    }

    @Override
    public Activity getOne(Long id) {
        return activityRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public Activity saveNew(Activity activity) {
        return activityRepository.save(activity);
    }

    @Override
    public Activity update(Activity activity, Long id) {
        Activity targetedActivity = activityRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        targetedActivity.setName(activity.getName());
        targetedActivity.setCourse(activity.getCourse());
        targetedActivity.setDate(activity.getDate());
        activityRepository.save(targetedActivity);
        return targetedActivity;
    }

    @Override
    public void delete(Long id) {
        activityRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        activityRepository.deleteById(id);
    }
}
