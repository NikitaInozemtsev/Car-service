package serv.sevices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import serv.models.KindOfWork;
import serv.repositories.KindOfWorkRepository;

import java.util.List;

@Service
public class WorkService {
    @Autowired
    private KindOfWorkRepository reps;

    public List<KindOfWork> getWorks() {
        return reps.findAll();
    }

    public void createKindOfWork(KindOfWork a) {
        reps.save(a);
    }

    public void delete(int id) {
        reps.delete(reps.findById(id).get());
    }

    public KindOfWork getWorkById(int kindOfWorkId) {
        return reps.findById(kindOfWorkId).get();
    }
}
