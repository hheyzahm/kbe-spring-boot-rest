package springboot.kberest.web.mappers;


import org.mapstruct.Mapper;
import springboot.kberest.domain.Beer;
import springboot.kberest.web.model.BeerDTO;

/**
 * @Created 03 04 2023 - 9:49 AM
 * @Author Hazeem Hassan
 */
@Mapper(uses = {DateMapper.class})
public interface BeerMapper {
    BeerDTO beerToBeerDto(Beer beer);

    BeerDTO beerToBeerDtoWithInventory(Beer beer);

    Beer beerDtoToBeer(BeerDTO dto);
}
