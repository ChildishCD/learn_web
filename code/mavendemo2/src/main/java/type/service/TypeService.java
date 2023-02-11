package type.service;

import lombok.NoArgsConstructor;
import type.model.FtypeModel;
import type.model.StypeModel;
import type.dao.TypeDAO;

import java.util.List;

@NoArgsConstructor
public class TypeService {
    private final TypeDAO typeDAO = new TypeDAO();

    public List<FtypeModel> selectAllFtype(){
        return typeDAO.selectAllFtype();
    }

    public List<StypeModel> selectAllStype(){
        return typeDAO.selectAllStype();
    }

}
