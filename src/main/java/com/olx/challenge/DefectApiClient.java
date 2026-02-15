package com.olx.challenge;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * API client interface for fetching defect data.
 */
public interface DefectApiClient {

    /**
     * Fetches all known defects for the given car models.
     *
     * @param models set of car model names to query
     * @return list of defects associated with those models
     */
    List<Defect> findDefectsForModels(Set<String> models);
}
