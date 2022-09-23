package uk.gov.gchq.magmacore.service.dto;

/**
 * A Data Transfer Object for sign and pattern data.
 */
public record SignPatternDto(String sign, String pattern, String representationByPattern) {
}
