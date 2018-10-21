package com.bbs.service.view;

import java.util.List;

import com.bbs.model.view.BBSBigBoard;
import com.bbs.model.view.BBSSmallBoard;

public interface IIndexService {

	List<BBSBigBoard> queryBigBoardAll();

	List<BBSSmallBoard> querySmallBoardAll();

}
