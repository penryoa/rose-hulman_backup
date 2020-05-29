import druid_backend

def redis_to_druid_import(spec):
    spec = spec.decode('utf-8')
    druid_backend.upload(spec)
