//package com.pfeffer.springcleanarchtemplate.infra.database.mongo.gateway;
//
//import com.pfeffer.springcleanarchtemplate.domain.entity.EntityBO;
//import com.pfeffer.springcleanarchtemplate.domain.exception.InternalValidationException;
//import com.pfeffer.springcleanarchtemplate.domain.gateway.support.Gateway;
//import com.pfeffer.springcleanarchtemplate.domain.pagination.PageContent;
//import com.pfeffer.springcleanarchtemplate.domain.pagination.Pagination;
//import com.pfeffer.springcleanarchtemplate.domain.utils.InternalValidation;
//import com.pfeffer.springcleanarchtemplate.infra.adapter.SpringPageAdapter;
//import com.pfeffer.springcleanarchtemplate.infra.adapter.SpringPageableAdapter;
//import com.pfeffer.springcleanarchtemplate.infra.database.postgres.gateway.PgGateway;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.Optional;
//import java.util.function.Function;
//
//public class MongoGateway<BO extends EntityBO, T, ID>
//        implements Gateway<BO, ID> {
//
//    /**
//     * A function that converts the entity {@link T} to a business object
//     * {@link BO}.
//     */
//    protected final Function<T, BO> toDomainMapper;
//
//    /**
//     * A function that converts the business object {@link BO} to the entity
//     * {@link T}.
//     */
//    protected final Function<BO, T> toEntityMapper;
//
//    /**
//     * The Spring {@link MongoRepository} that will be used for data access.
//     */
//    private final MongoRepository<T, ID> repository;
//
//    /**
//     * Constructs a new {@link PgGateway} with the specified JPA repository.
//     *
//     * @param repository     the JPA repository used for data access. Must not
//     *                       be {@code null}.
//     * @param toDomainMapper a function that converts the entity to a business
//     *                       object. Must not be {@code null}
//     * @param toEntityMapper a function that converts the business object to an
//     *                       entity. Must not be {@code null}
//     * @throws InternalValidationException if any of the arguments is {@code null}
//     */
//    public MongoGateway(MongoRepository<T, ID> repository,
//                        Function<T, BO> toDomainMapper,
//                        Function<BO, T> toEntityMapper) {
//
//        InternalValidation.notNull(repository, "MongoRepository must not be null");
//        InternalValidation.notNull(toDomainMapper, "Function toDomainMapper must not be null");
//        InternalValidation.notNull(toEntityMapper, "Function toEntityMapper must not be null");
//
//        this.repository = repository;
//        this.toDomainMapper = toDomainMapper;
//        this.toEntityMapper = toEntityMapper;
//    }
//
//    /**
//     * Checks if an entity with the given ID exists.
//     *
//     * @param id the ID to check. Must not be {@code null}.
//     * @return {@code true} if an entity with the given ID exists, {@code false}
//     * otherwise.
//     */
//    @Override
//    public boolean existsById(ID id) {
//        return repository.existsById(id);
//    }
//
//    /**
//     * Returns the count of all entities.
//     *
//     * @return the total number of entities.
//     */
//    @Override
//    public long count() {
//        return repository.count();
//    }
//
//    /**
//     * Finds an entity by its ID.
//     *
//     * @param id the ID of the entity to find. Must not be {@code null}.
//     * @return an {@code Optional} containing the found entity or an empty
//     * {@code Optional} if no entity with the given ID exists.
//     */
//    @Override
//    public Optional<BO> findById(ID id) {
//        Optional<T> entity = repository.findById(id);
//
//        return entity.map(toDomainMapper);
//    }
//
//    /**
//     * Returns all entities matching the given IDs.
//     *
//     * @param ids the IDs of the entities to retrieve. Must not be {@code null}.
//     * @return a list of entities corresponding to the given IDs, will never be
//     * {@code null}.
//     */
//    @Override
//    public List<BO> findByIds(Iterable<ID> ids) {
//        List<T> entities = repository.findAllById(ids);
//
//        return entities.stream()
//                .map(toDomainMapper)
//                .toList();
//    }
//
//    /**
//     * Returns all entities.
//     *
//     * @return a list of all entities, will never be {@code null}.
//     */
//    @Override
//    public List<BO> findAll() {
//        List<T> entities = repository.findAll();
//
//        return entities.stream()
//                .map(toDomainMapper)
//                .toList();
//    }
//
//    /**
//     * Finds all entities with optional sorting parameters.
//     *
//     * @param sort optional sorting parameters.
//     * @return a list of all sorted entities, will never be {@code null}.
//     */
//    @Override
//    public List<BO> findAll(String... sort) {
//        Sort sortRequest = Sort.by(sort);
//
//        List<T> entities = repository.findAll(sortRequest);
//
//        return entities.stream()
//                .map(toDomainMapper)
//                .toList();
//    }
//
//    /**
//     * Finds all entities with pagination and optional sorting.
//     * <p>
//     * If there's no entities for the given pagination, the {@code content} of
//     * {@link PageContent} will be an empty list.
//     *
//     * @param pagination The pagination parameters that define the page number,
//     *                   the size of the page, and optional sorting criteria.
//     * @return a paginated list of entities.
//     */
//    @Override
//    public PageContent<BO> findAll(Pagination pagination) {
//        Pageable pageRequest = SpringPageableAdapter.toPageable(pagination);
//
//        Page<T> entities = repository.findAll(pageRequest);
//
//        return new SpringPageAdapter<>(entities.map(toDomainMapper));
//    }
//
//    /**
//     * Saves a given entity.
//     *
//     * @param bo the entity to save. Must not be {@code null}.
//     * @return the saved entity.
//     */
//    @Override
//    @Transactional
//    public BO save(BO bo) {
//        T entity = toEntityMapper.apply(bo);
//
//        entity = repository.save(entity);
//
//        return toDomainMapper.apply(entity);
//    }
//
//    /**
//     * Saves all given entities.
//     *
//     * @param bos the entities to save. Must not be {@code null}.
//     * @return the list of saved entities, will never be {@code null}.
//     */
//    @Override
//    @Transactional
//    public List<BO> saveAll(Iterable<? extends BO> bos) {
//        List<T> entities = new ArrayList<>();
//
//        for (BO bo : bos) {
//            entities.add(toEntityMapper.apply(bo));
//        }
//
//        entities = repository.saveAll(entities);
//
//        return entities.stream()
//                .map(toDomainMapper)
//                .toList();
//    }
//
//    /**
//     * Deletes the entity with the given ID.
//     *
//     * @param id the ID of the entity to delete. Must not be {@code null}.
//     */
//    @Override
//    @Transactional
//    public void deleteById(ID id) {
//        repository.deleteById(id);
//    }
//
//    /**
//     * Deletes a given entity.
//     *
//     * @param bo the entity to delete. Must not be {@code null}.
//     */
//    @Override
//    @Transactional
//    public void delete(BO bo) {
//        T entity = toEntityMapper.apply(bo);
//
//        repository.delete(entity);
//    }
//
//    /**
//     * Deletes entities by their IDs.
//     *
//     * @param ids the IDs of the entities to delete. Must not be {@code null}.
//     */
//    @Override
//    @Transactional
//    public void deleteAllById(Iterable<? extends ID> ids) {
//        repository.deleteAllById(ids);
//    }
//
//    /**
//     * Deletes all given entities.
//     *
//     * @param bos the entities to delete. Must not be {@code null}.
//     */
//    @Override
//    @Transactional
//    public void deleteAll(Iterable<? extends BO> bos) {
//        Collection<T> entities = new ArrayList<>();
//
//        for (BO bo : bos) {
//            entities.add(toEntityMapper.apply(bo));
//        }
//
//        repository.deleteAll(entities);
//    }
//
//    /**
//     * Deletes all entities managed by the repository.
//     */
//    @Override
//    @Transactional
//    public void deleteAll() {
//        repository.deleteAll();
//    }
//
//}
