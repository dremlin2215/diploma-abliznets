package com.minsk.BSU.abliznets.cafe.util;

import com.minsk.BSU.abliznets.cafe.api.util.BonusHelper;
import com.minsk.BSU.abliznets.cafe.entitie.Bonus;

import java.util.List;
import java.util.Optional;

public class BonusHelperImpl implements BonusHelper {

    @Override
    public Bonus findBonusById(List<Bonus> bonuses, int bonusID) {
        Optional<Bonus> foundBonus = bonuses.stream()
                .filter(bonus -> (bonus.getID() == bonusID))
                .findFirst();

        return foundBonus.orElse(null);
    }
}
