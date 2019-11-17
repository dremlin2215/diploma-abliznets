package com.minsk.BSU.abliznets.cafe.api.util;

import com.minsk.BSU.abliznets.cafe.entitie.Bonus;

import java.util.List;

public interface BonusHelper {
    Bonus findBonusById(List<Bonus> bonuses, int bonusID);
}
