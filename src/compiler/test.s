.data
.comm	a,4,4

.comm	b,4,4

.text
	.align 4
.globl  func
func:
func_bb2:
func_bb3:
	movl	$123, %EAX
	movl	%ESI, %EAX
func_bb4:
	ret
.globl  main
main:
main_bb2:
	pushq	%R15
main_bb3:
	movl	$1, %EAX
	movl	%EAX, %EDI
	movl	$2, %EAX
	movl	%EAX, %ESI
	call	func
main_bb4:
	popq	%R15
	ret
