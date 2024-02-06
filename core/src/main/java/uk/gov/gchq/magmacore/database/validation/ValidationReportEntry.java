package uk.gov.gchq.magmacore.database.validation;

/**
 * An implementation agnostic model validation report entry.
 */
public record ValidationReportEntry(String type, Object additionalInformation, String description) {}

