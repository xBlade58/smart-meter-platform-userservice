package at.fhv.se.platform.adapter.postgresdb;

import at.fhv.se.platform.adapter.postgresdb.mapper.HouseholdDBEntity;
import at.fhv.se.platform.domain.port.outbound.persistence.HouseholdRepository;
import at.fhv.se.platform.domain.model.Household;
import at.fhv.se.platform.domain.model.HouseholdType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Justin Ströhle
 * 14.12.2023
 */

@Repository
public class PostgresHouseholdRepository implements HouseholdRepository {

    @Autowired
    private PostgresJPAHousehold postgresJPAHousehold;

    @Override
    public void save(Household household) {
        this.postgresJPAHousehold.save(mapModelToDBEntity(household));
    }

    @Override
    public List<Household> getAllHouseholds() {
        return this.postgresJPAHousehold.findAll().stream()
                .map(PostgresHouseholdRepository::mapDBEntityToModel)
                .collect(Collectors.toList());
    }

    @Override
    public Household getHousehold(String id) {
        Optional<HouseholdDBEntity> userDBEntityOptional = this.postgresJPAHousehold.findById(id);
        return userDBEntityOptional.map(PostgresHouseholdRepository::mapDBEntityToModel).orElse(null);
    }

    private static HouseholdDBEntity mapModelToDBEntity(Household model) {
        return new HouseholdDBEntity(model.getId(), model.getStreet(), model.getStreetNo(), model.getDoorNo(),
                model.getCity(), model.getZip(), model.getCountry(), model.getType().toString(), model.getSize(),
                model.getResidentsNo());
    }

    private static Household mapDBEntityToModel(HouseholdDBEntity dbEntity) {
        return new Household(dbEntity.getId(), dbEntity.getStreet(), dbEntity.getStreetNo(), dbEntity.getDoorNo(),
                dbEntity.getCity(), dbEntity.getZip(), dbEntity.getCountry(), HouseholdType.valueOf(dbEntity.getType()),
                dbEntity.getSize(), dbEntity.getResidentsNo());
    }
}