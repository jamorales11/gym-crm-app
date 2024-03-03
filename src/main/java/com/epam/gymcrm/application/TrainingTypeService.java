package com.epam.gymcrm.application;

import com.epam.gymcrm.application.dto.trainingtype.TrainingTypeDto;
import com.epam.gymcrm.application.dto.trainingtype.TrainingTypeListDto;

import java.util.List;

public interface TrainingTypeService {

    List<TrainingTypeListDto> getTrainingTypeList();
}
