package com.minsk.BSU.abliznets.cafe.service.impl;

import com.minsk.BSU.abliznets.cafe.api.repository.Repository;
import com.minsk.BSU.abliznets.cafe.api.service.BonusService;
import com.minsk.BSU.abliznets.cafe.entitie.Bonus;
import com.minsk.BSU.abliznets.cafe.repository.RepositoryException;
import com.minsk.BSU.abliznets.cafe.repository.RepositoryFactory;
import com.minsk.BSU.abliznets.cafe.repository.specification.bonus.BonusByUserIDSpecification;
import com.minsk.BSU.abliznets.cafe.repository.specification.bonus.BonusByUserIDWithLimitSpecification;
import com.minsk.BSU.abliznets.cafe.repository.specification.bonus.LastBonusSpecification;
import com.minsk.BSU.abliznets.cafe.service.ServiceException;

import java.util.List;

public class BonusServiceImpl implements BonusService {
    private static final Integer FIRST = 0;

    @Override
    public List<Bonus> getClientBonuses(int clientID) throws ServiceException {
        List<Bonus> bonuses;

        try (RepositoryFactory factory = new RepositoryFactory()) {
            Repository<Bonus> bonusRepository = factory.bonusRepository();
            bonuses = bonusRepository.query(new BonusByUserIDSpecification(clientID));
        } catch (RepositoryException e) {
            throw new ServiceException("Getting clients bonuses error.", e);
        }

        return bonuses;
    }

    @Override
    public void deleteBonus(Bonus bonus) throws ServiceException {
        try (RepositoryFactory factory = new RepositoryFactory()) {
            Repository<Bonus> bonusRepository = factory.bonusRepository();
            bonusRepository.remove(bonus);
        } catch (RepositoryException e) {
            throw new ServiceException("Deleting bonus error.", e);
        }
    }

    @Override
    public void addBonus(Bonus bonus) throws ServiceException {
        try (RepositoryFactory factory = new RepositoryFactory()) {
            Repository<Bonus> bonusRepository = factory.bonusRepository();
            bonusRepository.save(bonus);
        } catch (RepositoryException e) {
            throw new ServiceException("Adding bonus error.", e);
        }
    }

    @Override
    public Bonus getLastBonus() throws ServiceException {
        Bonus lastBonus;

        try (RepositoryFactory factory = new RepositoryFactory()) {
            Repository<Bonus> bonusRepository = factory.bonusRepository();
            List<Bonus> bonuses = bonusRepository.query(new LastBonusSpecification());
            lastBonus = bonuses.get(FIRST);
        } catch (RepositoryException e) {
            throw new ServiceException("Getting last bonus error.", e);
        }

        return lastBonus;
    }

    @Override
    public List<Bonus> getClientBonuses(int userID, int skippingPagesNumber, int recordsCount)
            throws ServiceException {
        List<Bonus> bonuses;

        try (RepositoryFactory factory = new RepositoryFactory()) {
            Repository<Bonus> bonusRepository = factory.bonusRepository();
            bonuses = bonusRepository.query(new BonusByUserIDWithLimitSpecification(
                    userID,
                    skippingPagesNumber,
                    recordsCount
            ));
        } catch (RepositoryException e) {
            throw new ServiceException("Getting clients bonuses error.", e);
        }

        return bonuses;
    }
}
