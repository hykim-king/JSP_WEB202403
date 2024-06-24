package com.pcwk.ehr.code;

import java.util.List;

import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.PLog;

public class CodeService implements PLog {
   private CodeDao dao;
   
   public CodeService() {
	   dao = new CodeDao();
   }
   
   public List<CodeVO> doRetrieve(DTO search) {
	   return dao.doRetrieve(search);
   }
}
