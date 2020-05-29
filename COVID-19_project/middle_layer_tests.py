import middle_layer

print("You should see this:")
print("Hello 0")
print("World! 1")

n = 0
for msg in ('Hello', 'World!'):
    middle_layer.log_update('test', [msg, n])
    n += 1
