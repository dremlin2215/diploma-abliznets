package com.minsk.BSU.abliznets.cafe.command.impl.get;

import com.minsk.BSU.abliznets.cafe.api.service.BonusService;
import com.minsk.BSU.abliznets.cafe.command.impl.AbstractCommand;
import com.minsk.BSU.abliznets.cafe.entitie.Bonus;
import com.minsk.BSU.abliznets.cafe.service.impl.BonusServiceImpl;
import com.minsk.BSU.abliznets.cafe.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public abstract class GetBonusesAbstractCommand extends AbstractCommand {
    private HttpServletRequest request;

    public GetBonusesAbstractCommand(HttpServletRequest request) {
        this.request = request;
    }

    protected List<Bonus> getBonuses(int clientID) throws ServiceException {
        HttpSession session = request.getSession();
        BonusService service = new BonusServiceImpl();

        int recordsCount = findRecordsCount(request);
        int pageNumber = findPageNumber(request);

        List<Bonus> bonuses;
        if (pageNumber == 1) {
            bonuses = service.getClientBonuses(clientID);
            int bonusesCount = bonuses.size();
            findPageCount(session, bonusesCount, recordsCount);

            if (bonusesCount > recordsCount) {
                bonuses = bonuses.subList(0, recordsCount);
            }
        } else {
            int skippingPagesNumber = pageNumber - 1;
            bonuses = service.getClientBonuses(clientID, skippingPagesNumber, recordsCount);
        }

        return bonuses;
    }
}
