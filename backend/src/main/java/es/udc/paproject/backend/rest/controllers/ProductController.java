package es.udc.paproject.backend.rest.controllers;

import static es.udc.paproject.backend.rest.dtos.CategoryConversor.toCategoryDtos;
import static es.udc.paproject.backend.rest.dtos.ProductConversor.toProductInfoDto;
import static es.udc.paproject.backend.rest.dtos.ProductConversor.toUserProductsDtos;
import static es.udc.paproject.backend.rest.dtos.ProductConversor.toProductSummaryDtos;

import java.util.List;
import java.util.Locale;

import es.udc.paproject.backend.model.entities.Product;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.InvalidDateException;
import es.udc.paproject.backend.model.services.Block;
import es.udc.paproject.backend.model.services.ProductService;
import es.udc.paproject.backend.rest.common.ErrorsDto;
import es.udc.paproject.backend.rest.dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/catalog")
public class ProductController {

    private final static String INVALID_DATE_EXCEPTION_CODE = "project.exceptions.InvalidDateException";

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private ProductService productService;

    @ExceptionHandler(InvalidDateException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorsDto handleInvalidDateException(InvalidDateException exception, Locale locale) {

        String errorMessage = messageSource.getMessage(INVALID_DATE_EXCEPTION_CODE,
                new Object[] {exception.getKey()}, INVALID_DATE_EXCEPTION_CODE, locale);

        return new ErrorsDto(errorMessage);

    }

    @GetMapping("/categories")
    public List<CategoryDto> findAllCategories() {
        return toCategoryDtos(productService.findAllCategories());
    }

    @PostMapping("/add")
    public Long announceProduct(@RequestAttribute Long userId, @Validated({ProductDto.AllValidations.class})
                                                               @RequestBody ProductDto productDto)
            throws InstanceNotFoundException {
        return productService.announceProduct(userId, productDto.getName(), productDto.getDescription(),
                productDto.getMinutes(), productDto.getStartPrice(), productDto.getShipInfo(),
                productDto.getCategoryId()).getId();
    }

    @GetMapping("/products/{id}")
    public ProductInfoDto findProductById(@PathVariable Long id) throws InstanceNotFoundException {
        return toProductInfoDto(productService.getProductInfo(id));
    }

    @GetMapping("/products/user")
    public BlockDto<UserProductsDto> findUserProducts(@RequestAttribute Long userId,
                                                      @RequestParam(defaultValue="0") int page) {

        Block<Product> productBlock = productService.findUserProducts(userId, page, 2);
        return new BlockDto<>(toUserProductsDtos(productBlock.getItems()), productBlock.getExistMoreItems());
    }

    @GetMapping("/products")
    public BlockDto<ProductSummaryDto> findProducts(
            @RequestParam(required=false) Long categoryId,
            @RequestParam(required=false) String keywords,
            @RequestParam(defaultValue="0") int page) {

        Block<Product> productBlock = productService.findProducts(categoryId,
                keywords != null ? keywords.trim() : null, page, 2);

        return new BlockDto<>(toProductSummaryDtos(productBlock.getItems()), productBlock.getExistMoreItems());

    }

}
