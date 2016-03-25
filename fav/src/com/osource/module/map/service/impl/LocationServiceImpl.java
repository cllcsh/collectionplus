package com.osource.module.map.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.base.constants.Codebook;
import com.osource.base.dao.LocationDao;
import com.osource.base.dao.TerminalDao;
import com.osource.base.model.Location;
import com.osource.base.model.Terminal;
import com.osource.core.exception.IctException;
import com.osource.module.map.service.LocationService;
import com.osource.orm.ibatis.BaseServiceImpl;

@Service("locationService")
@Scope("prototype")
@Transactional
public class LocationServiceImpl extends BaseServiceImpl<Location> implements LocationService {
    @Autowired
    private TerminalDao terminalDao;

    public List<Integer> saveLocations(List<Integer> terminalIds) throws IctException {
        logger.debug("Enter Func:saveLocations,terminalIds is " + terminalIds.toString());
        List<Integer> result = new ArrayList();
        List<Terminal> terminals = terminalDao.findByIds(terminalIds);
        for (Terminal terminal : terminals) {
            Location location = new Location();
            location.setTerminalId(terminal.getId());
            location.setRailingsId(terminal.getRailingsId());
            location.setRailings(terminal.getRailings());
            location.setLocNum(terminal.getLocationNum());
            location.setLocImsi(terminal.getLocationImsi());
            location.setType(Codebook.TB_LOCATION_TYPE_1);
            location.setMod(terminal.getType());
            // location.setDeptId(getUserSession().getDeptId());
            location.setInsertId(getUserSession().getUserId());
            this.getDao().save(location);
            result.add(location.getId());

            this.logger.debug("terminal.type = " + terminal.getType());
            this.logger.debug("location.mod = " + location.getMod());
        }
        logger.debug("Save location terminalIds success.");
        return result;
    }

    public List<Integer> saveLocationByMobile(String locNum) throws IctException {
        logger.debug("Enter Func:saveLocationsByMobile,locNum is " + locNum);
        List<Integer> result = new ArrayList();

        String[] loc_num = locNum.split(",");

        for (int i = 0; i < loc_num.length; i++) {
            Terminal terminal = null;
            if (loc_num[i] != null && loc_num[i].length() == 11) {
                terminal = terminalDao.selectByLocNum(loc_num[i]);
            }

            if (terminal == null || "".equals(terminal)) {
                return null;
            }

            Location location = new Location();
            location.setTerminalId(terminal.getId());
            location.setRailingsId(terminal.getRailingsId());
            location.setRailings(terminal.getRailings());
            location.setLocNum(terminal.getLocationNum());
            location.setLocImsi(terminal.getLocationImsi());
            location.setType(Codebook.TB_LOCATION_TYPE_1);
            location.setMod(terminal.getType());

            if (this.getUserSession() == null || "".equals(this.getUserSession())) {
                // location.setDeptId(terminal.getDeptId());
                location.setInsertId(terminal.getStaffId());
            } else {
                // location.setDeptId(getUserSession().getDeptId());
                location.setInsertId(getUserSession().getUserId());
            }

            this.getDao().save(location);

            result.add(location.getId());

        }
        logger.debug("Save location  success.");

        return result;
    }

    @Override
    protected LocationDao getDao() {
        return (LocationDao) super.getDao();
    }

    @Autowired
    public void setDao(LocationDao dao) {
        super.setDao(dao);
    }

}
