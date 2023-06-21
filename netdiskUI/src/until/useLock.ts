import { ref } from 'vue';

export function useLock() {
  const lock = ref(false);

  function acquireLock() {
    if (lock.value) {
      return false; // 如果锁已经被占用，则返回false
    }

    lock.value = true; // 加锁
    return true;
  }

  function releaseLock() {
    lock.value = false; // 解锁
  }

  return {
    acquireLock,
    releaseLock
  };
}