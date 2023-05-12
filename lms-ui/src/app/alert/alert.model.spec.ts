import {Alert} from './alert.model';

describe('Alert', () => {
  it('should create an instance', () => {
    expect(new Alert('warning', 'test')).toBeTruthy();
  });
});
