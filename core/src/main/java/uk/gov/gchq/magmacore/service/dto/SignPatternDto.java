package uk.gov.gchq.magmacore.service.dto;

/**
 * A Data Transfer Object for sign and pattern data.
 *
 * @param sign Some sign.
 * @param pattern Some pattern.
 * @param representationByPattern Some representationByPattern.
 */
public record SignPatternDto(String sign, String pattern, String representationByPattern) {
}
