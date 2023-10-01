create table event_source (
    event_id uuid primary key,
    local_event_id bigint not null,
    event_type varchar not null,
    event_version varchar not null,
    aggregate_id varchar not null,
    aggregate_type varchar not null,
    serialized_data jsonb,
    created_at timestamptz not null default now(),
    created_by varchar
);