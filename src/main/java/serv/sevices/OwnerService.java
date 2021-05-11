package serv.sevices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import serv.models.Owner;
import serv.repositories.OwnerRepository;

import java.util.List;

@Service
public class OwnerService {
    @Autowired
    private OwnerRepository reps;

    public List<Owner> getOwners() {
        return reps.findAll();
    }

    public void createOwner(Owner a) {
        reps.save(a);
    }

    public Owner getOwnerById(int ownerId) {
        return reps.findById(ownerId).get();
    }
}
