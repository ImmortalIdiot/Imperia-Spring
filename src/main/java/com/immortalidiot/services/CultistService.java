package com.immortalidiot.services;

import com.immortalidiot.services.dtos.CultistDTO;
import com.immortalidiot.services.dtos.CultistResponseDTO;

public interface CultistService {
    CultistResponseDTO promoteCultist(CultistDTO cultistDTO);
}
