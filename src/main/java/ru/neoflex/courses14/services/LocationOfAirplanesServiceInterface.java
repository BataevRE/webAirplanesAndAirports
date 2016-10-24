package ru.neoflex.courses14.services;//To change this template use File | Settings | File Templates.

import ru.neoflex.courses14.EntityNotFoundException;

public interface LocationOfAirplanesServiceInterface {
    abstract void addLink(Long airportId, Long airplaneId) throws EntityNotFoundException;

    abstract void removeLink(Long airportId, Long airplaneId) throws EntityNotFoundException;
}
